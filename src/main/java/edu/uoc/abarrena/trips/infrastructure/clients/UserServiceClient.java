package edu.uoc.abarrena.trips.infrastructure.clients;

import edu.uoc.abarrena.trips.infrastructure.clients.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "users-service", url = "http://localhost:8082")
public interface UserServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/companies/{id}", consumes = "application/json")
    public Result findCompanyById(@PathVariable("id") Long id);
}
