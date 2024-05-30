package eu.api.infrastructure.config;

import eu.api.application.command.create_app.CreateAppCommand;
import eu.api.application.command.create_service.CreateServiceCommand;
import eu.api.infrastructure.rest.dto.AppDTO;
import eu.api.infrastructure.rest.dto.ServiceDTO;
import eu.api.infrastructure.rest.filter.ErrorFilter;
import eu.api.infrastructure.rest.handler.create_app.CreateAppHandler;
import eu.api.infrastructure.rest.handler.create_service.CreateServiceHandler;
import eu.api.infrastructure.rest.handler.get_apps_by_service_name.GetAppsByServiceNameHandler;
import eu.api.infrastructure.rest.handler.get_services_by_name.GetServicesByNameHandler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "RMIT", version = "1.0", description = "Documentation APIs v1.0"))
class AppRouterConfig {
    private final CreateAppHandler createApp;
    private final CreateServiceHandler createService;
    private final GetAppsByServiceNameHandler getAppsByServiceName;
    private final GetServicesByNameHandler getServicesByName;

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/application",
                    method = RequestMethod.GET,
                    produces = "application/json",
                    operation = @Operation(
                            operationId = "getAppsByServiceName",
                            tags = "Rakendus",
                            summary = "Rakenduse otsing",
                            description = "Mina kui kasutaja saan otsida teenuse nime järgi ning vastusena saan rakenduse koos tema lisaväljade infoga.",
                            parameters = {
                                    @Parameter(
                                            in = ParameterIn.QUERY,
                                            name = "name",
                                            description = "Teenuse nimi",
                                            schema = @Schema(implementation = String.class)
                                    )
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AppDTO.class))))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/application",
                    method = RequestMethod.POST,
                    consumes = "application/json",
                    produces = "application/json",
                    operation = @Operation(
                            operationId = "createApp",
                            tags = "Rakendus",
                            summary = "Rakenduse lisamine",
                            description = "Mina kui kasutaja saan veebilehe kaudu sisestada uue rakenduse.",
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(implementation = CreateAppCommand.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = AppDTO.class))),
                                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                            }
                    )
            ),
            @RouterOperation(
                    path = "/application/service",
                    method = RequestMethod.GET,
                    produces = "application/json",
                    operation = @Operation(
                            operationId = "getServicesByName",
                            tags = "Teenus",
                            summary = "Teenuse otsing",
                            description = "Mina kui kasutaja saan otsida rakenduse nime järgi ning vastusena saan selle rakenduse kõik teenused koos lisaväljade infoga.",
                            parameters = {
                                    @Parameter(
                                            in = ParameterIn.QUERY,
                                            name = "name",
                                            description = "Rakenduse nimi",
                                            schema = @Schema(implementation = String.class)
                                    )
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ServiceDTO.class))))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/application/service",
                    method = RequestMethod.POST,
                    consumes = "application/json",
                    produces = "application/json",
                    operation = @Operation(
                            operationId = "createService",
                            tags = "Teenus",
                            summary = "Teenuse lisamine",
                            description = "Mina kui kasutaja saan sisestada uue teenuse.",
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(implementation = CreateServiceCommand.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ServiceDTO.class))),
                                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
                            }
                    )
            )
    })
    RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route()
                .GET("/application", getAppsByServiceName::handle)
                .POST("/application", createApp::handle)
                .GET("/application/service", getServicesByName::handle)
                .POST("/application/service", createService::handle)
                .filter(ErrorFilter.filter())
                .build();
    }
}
