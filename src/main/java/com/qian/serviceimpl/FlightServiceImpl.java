package com.qian.serviceimpl;
import com.qian.dao.IFlightDAO;
import com.qian.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service("flightService")
public class FlightServiceImpl implements IFlightService {
    @Autowired
    IFlightDAO flightInfoDAOImpl;
    @Override
    public List<Map<String, String>> findAllFlight() {
        System.out.println("service test ok");
        List<Map<String, String>> allFlightInfo = flightInfoDAOImpl.findAllFlightInfo();
        return allFlightInfo;
    }
}
