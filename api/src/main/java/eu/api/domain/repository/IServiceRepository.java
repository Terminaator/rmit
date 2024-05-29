package eu.api.domain.repository;

import eu.api.domain.modal.Service;

import java.util.List;

public interface IServiceRepository {
    List<Service> findAllApps(String appName);

    List<Service> findAll();
}
