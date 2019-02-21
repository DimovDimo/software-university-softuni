using System;
using System.Collections;
using System.Collections.Generic;
using Wintellect.PowerCollections;
using System.Linq;

public class Judge : IJudge
{
    HashSet<int> byUserId = new HashSet<int>();
    HashSet<int> byContestId = new HashSet<int>();
    Dictionary<int, Submission> byId =
        new Dictionary<int, Submission>();

    public void AddContest(int contestId)
    {
        byContestId.Add(contestId);
    }

    public void AddSubmission(Submission submission)
    {
        if(!byUserId.Contains(submission.UserId) ||
            !byContestId.Contains(submission.ContestId))
        {
            throw new InvalidOperationException();
        }

        if (this.byId.ContainsKey(submission.Id))
        {
            return;
        }

        byId.Add(submission.Id, submission);
    }

    public void AddUser(int userId)
    {
        byUserId.Add(userId);
    }

    public void DeleteSubmission(int submissionId)
    {
        if (!byId.ContainsKey(submissionId))
        {
            throw new InvalidOperationException();
        }
        
        byId.Remove(submissionId);
       
    }

    public IEnumerable<Submission> GetSubmissions()
    {
        return byId.Values
            .OrderBy(x => x.Id);
    }

    public IEnumerable<int> GetUsers()
    {
        return byUserId.OrderBy(x => x);
    }

    public IEnumerable<int> GetContests()
    {
        return byContestId.OrderBy(x => x);
    }

    public IEnumerable<Submission> SubmissionsWithPointsInRangeBySubmissionType(int minPoints, int maxPoints, SubmissionType submissionType)
    {
        return byId.Values
            .Where(x => x.Type == submissionType
                   && minPoints <= x.Points
                   && x.Points <= maxPoints);
    }

    public IEnumerable<int> ContestsByUserIdOrderedByPointsDescThenBySubmissionId(int userId)
    {
        return byId.Values
            .Where(x => x.UserId == userId)
            .OrderByDescending(x => x.Points)
            .ThenBy(x => x.Id)
            .Select(x => x.ContestId)
            .Distinct();
    }

    public IEnumerable<Submission> SubmissionsInContestIdByUserIdWithPoints(int points, int contestId, int userId)
    {
        if (!byUserId.Contains(userId) ||
            !byContestId.Contains(contestId))
        {
            throw new InvalidOperationException();
        }

        var result = byId.Values
            .Where(x => x.Points == points
                   && x.ContestId == contestId
                   && x.UserId == userId);

        if(result.Count() == 0)
        {
            throw new InvalidOperationException();
        }

        return result;
    }

    public IEnumerable<int> ContestsBySubmissionType(SubmissionType submissionType)
    {
        return byId.Values
            .Where(x => x.Type == submissionType)
            .Select(x => x.ContestId)
            .Distinct();
    }
}
