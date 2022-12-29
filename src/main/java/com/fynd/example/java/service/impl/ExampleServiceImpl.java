package com.fynd.example.java.service.impl;

import com.fynd.example.java.service.ExampleService;
import com.fynd.extension.utils.ExtensionContext;
import com.sdk.platform.PlatformClient;
import com.sdk.platform.PlatformModels;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Override
    public PlatformModels.ApplicationsResponse getApplications() {
        PlatformClient platformClient = ExtensionContext.get("platform-client", PlatformClient.class);
        PlatformModels.ApplicationsResponse applications = null;
        try {
            applications = platformClient.configuration.getApplications(1, 100, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return applications;
    }
}
