package {{path}}.{{entity.name}}.services;

import {{path}}.base.api.request.SearchRequest;
import {{path}}.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.mappings.{{entity.nameUpper}}Mapping;
import {{path}}.{{entity.name}}.model.{{entity.nameUpper}}Doc;
import {{path}}.{{entity.name}}.repository.{{entity.nameUpper}}Repository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class {{entity.nameUpper}}ApiService {
    private final {{entity.nameUpper}}Repository {{entity.name}}Repository;
    private final MongoTemplate mongoTemplate;

    public SearchResponse<{{entity.nameUpper}}Doc> search(SearchRequest request){
        Criteria criteria = new Criteria();
        if(request.getQuery() != null && request.getQuery() != ""){
            criteria = criteria.orOperator(
                    // TODO: Add query
                    //Criteria.where("firstName").regex(request.getQuery(), "i"),
            );
        }

        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, {{entity.nameUpper}}Doc.class);

        query.limit(request.getSize().intValue());
        query.skip(request.getSkip());
        List<{{entity.nameUpper}}Doc> {{entity.name}}Docs = mongoTemplate.find(query, {{entity.nameUpper}}Doc.class);

        return SearchResponse.of({{entity.name}}Docs, count);
    }

    public {{entity.nameUpper}}Doc create({{entity.nameUpper}}Request request) {

        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.instance().getRequest().convert(request);
        {{entity.name}}Doc = {{entity.name}}Repository.save({{entity.name}}Doc);

        return {{entity.name}}Doc;
    }

    public {{entity.nameUpper}}Doc update({{entity.nameUpper}}Request request) throws {{entity.nameUpper}}NotExistException {
        Optional<{{entity.nameUpper}}Doc> optional{{entity.nameUpper}}Doc = {{entity.name}}Repository.findById(request.getId());
        if(optional{{entity.nameUpper}}Doc.isPresent() == false){
            throw new {{entity.nameUpper}}NotExistException();
        }

        {{entity.nameUpper}}Doc oldDoc = optional{{entity.nameUpper}}Doc.get();

        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.instance().getRequest().convert(request);
        {{entity.name}}Doc.setId(oldDoc.getId());

        return {{entity.name}}Doc;
    }

    public Optional<{{entity.nameUpper}}Doc> findById(ObjectId id){
        return {{entity.name}}Repository.findById(id);
    }

    public void delete(ObjectId id){
        {{entity.name}}Repository.deleteById(id);
    }

}
