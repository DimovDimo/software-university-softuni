using System;
using System.Collections.Generic;
using System.Linq;

public class Judge : IJudge
{
    HashSet<int> users = new HashSet<int>();
    HashSet<int> contests = new HashSet<int>();
    Dictionary<int, Submission> bySubmissionId =
        new Dictionary<int, Submission>();

    public void AddContest(int contestId)
    {
        this.contests.Add(contestId);
    }

    public void AddSubmission(Submission submission)
    {
        if (this.bySubmissionId.ContainsKey(submission.Id))
        {
            return;
        }

        if (!this.users.Contains(submission.UserId) ||
            !this.contests.Contains(submission.ContestId))
        {
            throw new InvalidOperationException();
        }

        this.bySubmissionId.Add(submission.Id, submission);
    }

    public void AddUser(int userId)
    {
        this.users.Add(userId);
    }

    public void DeleteSubmission(int submissionId)
    {
        if (!this.bySubmissionId.ContainsKey(submissionId))
        {
            throw new InvalidOperationException();
        }

        Submission submission = this.bySubmissionId[submissionId];
        this.bySubmissionId.Remove(submissionId);
        
        //if (!this.bySubmissionId.ContainsKey(submissionId))
        //{
        //    throw new InvalidOperationException();
        //}
        //
        //Submission submission = this.bySubmissionId[submissionId];
        //this.bySubmissionId.Remove(submissionId);

        //this.bySubmissionId = this.bySubmissionId
        //    .Where(x => x.Key != submissionId)
        //    .ToDictionary(x => x.Key, y => y.Value);
    }

    public IEnumerable<Submission> GetSubmissions()
    {
        return this.bySubmissionId.Values
            .OrderBy(x => x.Id);
    }

    public IEnumerable<int> GetUsers()
    {
        return this.users.OrderBy(x => x);
    }

    public IEnumerable<int> GetContests()
    {
        return this.contests.OrderBy(x => x);
    }

    public IEnumerable<Submission> SubmissionsWithPointsInRangeBySubmissionType(int minPoints, int maxPoints, SubmissionType submissionType)
    {
        return this.bySubmissionId.Values
            .Where(x => x.Type == submissionType 
                        && minPoints <= x.Points
                        && x.Points <= maxPoints);
    }

    public IEnumerable<int> ContestsByUserIdOrderedByPointsDescThenBySubmissionId(int userId)
    {
        return this.bySubmissionId.Values
            .Where(x => x.UserId == userId)
            .OrderByDescending(x => x.Points)
            .ThenBy(x => x.Id)
            .Select(x => x.ContestId)
            .Distinct();
    }

    public IEnumerable<Submission> SubmissionsInContestIdByUserIdWithPoints(int points, int contestId, int userId)
    {
        var results = this.bySubmissionId.Values
            .Where(x => x.ContestId == contestId
                        && userId == x.UserId
                        && x.Points == points);

        if (!results.Any())
        {
            throw new InvalidOperationException();
        }

        return results;
    }

    public IEnumerable<int> ContestsBySubmissionType(SubmissionType submissionType)
    {
        return this.bySubmissionId.Values
            .Where(x => x.Type == submissionType)
            .Select(x => x.ContestId)
            .Distinct();
    }
}
