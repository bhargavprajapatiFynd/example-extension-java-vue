package com.fynd.example.java.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import com.fynd.example.java.service.impl.ExampleServiceImpl;
import com.fynd.extension.utils.ExtensionContext;
import com.sdk.platform.PlatformClient;
import com.sdk.platform.PlatformModels;
import com.sdk.platform.PlatformService;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class ExampleServiceTest {
    @InjectMocks
    ExampleServiceImpl exampleService;
    @Mock
    PlatformClient platformClient;

    @Mock
    PlatformService.ConfigurationService configurationService;

    @Test
    void getApplications() throws IOException {
        try(MockedStatic<ExtensionContext> context = Mockito.mockStatic(ExtensionContext.class)){

            context.when(()->ExtensionContext.get("platform-client", PlatformClient.class)).thenReturn(platformClient);

            PlatformModels.ApplicationsResponse res = new PlatformModels.ApplicationsResponse();

            ReflectionTestUtils.setField(platformClient, "configuration", configurationService);

            Mockito.when(configurationService.getApplications(anyInt(), anyInt(), anyString())).thenReturn(res);


            assertThat(exampleService.getApplications()).isEqualTo(res);
        }
    }
}
