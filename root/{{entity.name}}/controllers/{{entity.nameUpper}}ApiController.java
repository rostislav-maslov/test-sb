package {{path}}.{{entity.name}}.controllers;

import {{path}}.base.api.request.SearchRequest;
import {{path}}.base.api.response.OkResponse;
import {{path}}.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.api.response.{{entity.nameUpper}}Response;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.mappings.{{entity.nameUpper}}Mapping;
import {{path}}.{{entity.name}}.routes.{{entity.nameUpper}}ApiRoutes;
import {{path}}.{{entity.name}}.services.{{entity.nameUpper}}ApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "{{entity.nameUpper}} API")
public class {{entity.nameUpper}}ApiController {
    private final {{entity.nameUpper}}ApiService {{entity.name}}ApiService;

    @GetMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "Find {{entity.name}} by ID", notes = "Use this when you need full info about {{entity.nameUpper}}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "{{entity.nameUpper}} not found")
    }            )
    public OkResponse<{{entity.nameUpper}}Response> byId(
            @ApiParam(value = "{{entity.nameUpper}} ID") @PathVariable ObjectId id
    ) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of({{entity.nameUpper}}Mapping.instance()
                .getResponse().convert(
                        {{entity.name}}ApiService
                                .findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
                ));
    }

    @GetMapping({{entity.nameUpper}}ApiRoutes.ROOT)
    @ApiOperation(value = "Search {{entity.name}}s", notes = "Use this when you need find {{entity.name}}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success") })
    public OkResponse<SearchResponse<{{entity.nameUpper}}Response>> search(@ModelAttribute SearchRequest request) {
        return OkResponse.of({{entity.nameUpper}}Mapping.instance()
                .getSearch().convert(
                        {{entity.name}}ApiService
                                .search(request)
                ));
    }

    @PostMapping({{entity.nameUpper}}ApiRoutes.ROOT)
    @ApiOperation(value = "Create", notes = "Use this when you need create new {{entity.name}}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Invalid ID"), })
    public OkResponse<{{entity.nameUpper}}Response> create(@RequestBody {{entity.nameUpper}}Request request) throws {{entity.nameUpper}}ExistException {
        return OkResponse.of({{entity.nameUpper}}Mapping.instance()
                .getResponse().convert(
                        {{entity.name}}ApiService
                                .create(request)
                ));
    }

    @PutMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "Update", notes = "Use this when you need update")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "{{entity.nameUpper}} not found") })
    public OkResponse<{{entity.nameUpper}}Response> updateById(
            @ApiParam(value = "{{entity.nameUpper}} ID") @PathVariable ObjectId id,
            @RequestBody {{entity.nameUpper}}Request request) throws {{entity.nameUpper}}NotExistException {
        return OkResponse.of({{entity.nameUpper}}Mapping.instance()
                .getResponse().convert(
                        {{entity.name}}ApiService
                                .update(request)
                ));
    }

    @DeleteMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "delete", notes = "Delete {{entity.name}}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success") })
    public OkResponse<String> delete(
            @ApiParam(value = "{{entity.nameUpper}} ID") @PathVariable ObjectId id
    ){
        {{entity.name}}ApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}
