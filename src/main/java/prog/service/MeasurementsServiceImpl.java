package prog.service;

import org.hibernate.Session;
import prog.models.WeatherData;
import prog.util.database.DBConnector;

import java.util.List;

public class MeasurementsServiceImpl implements MeasurementsService {

    DBConnector dbConnector;

    public MeasurementsServiceImpl() {
        dbConnector = DBConnector.getInstance();
    }

    @Override
    public void saveAllMeasurements(List<WeatherData> weatherDataList) {

        Session session = DBConnector.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(weatherDataList);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<WeatherData> getAllMeasurements() {
        return null;
    }
}
