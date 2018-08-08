package contracts;

import exeptions.DuplicateModelException;
import models.boats.MotorBoat;

import java.util.List;

public interface Race
{
    int getDistance();

    int getWindSpeed ();

    int getOceanCurrentSpeed();

    Boolean getAllowsMotorboats ();

    void AddParticipant(MotorBoat boat) throws DuplicateModelException;

    List<MotorBoat> GetParticipants();
}
