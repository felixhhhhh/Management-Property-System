package cmtyMgmtSys.service;

import cmtyMgmtSys.dao.ResidentDao;
import cmtyMgmtSys.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentService {

    @Autowired
    private ResidentDao residentDao;

    public void addCustomer(Resident resident) {
        resident.getUser().setEnabled(true);

        // cart
        // customer

        residentDao.addResident(resident);
    }

    public Resident getCustomerByUserName(String username) {
        return residentDao.getResidentByUserName(username);
    }
}
