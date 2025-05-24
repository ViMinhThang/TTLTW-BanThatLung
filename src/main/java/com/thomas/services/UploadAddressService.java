package com.thomas.services;

import com.thomas.dao.AddressDao;
import com.thomas.dao.model.Address;
import com.thomas.dao.model.OrderDetails;

import java.io.IOException;
import java.util.List;

public class UploadAddressService {
    AddressDao addressDao;
    LocationService locationService = new LocationService();

    public UploadAddressService() {
        this.addressDao = new AddressDao();
    }

    public boolean addAddress(Address address) {
        return addressDao.insertOne(address);
    }

    public List<Address> getAddressList(int userId) throws IOException {
        List<Address> listAddress = addressDao.findAddressByUserId(userId);
        for (Address address : listAddress) {
            address.setProvinceName(getProvinceName(address.getProvinceId()));
            address.setDistrictName(getDistrictName(address.getProvinceId(), address.getDistrictId()));
            address.setWardName(getWardName(address.getDistrictId(),address.getWardId()));
        }
        return listAddress;
    }

    public void setDefault(int addressId, int userId) {
        if (addressDao.setAllUnuse(userId)) {
            addressDao.setDefault(addressId, userId);
        }
    }

    public String getProvinceName(int provinceId) throws IOException {
        return locationService.findProvinceName(provinceId);
    }

    public String getDistrictName(int provinceId, int districtId) throws IOException {
        return locationService.findDistrictName(provinceId, districtId);
    }

    public String getWardName(int districtId, String wardCode) throws IOException {
        return locationService.findWardName(districtId, wardCode);
    }
}
