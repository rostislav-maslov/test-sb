package {{path}}.{{entity.name}}.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
@ApiModel(value = "{{entity.nameUpper}}Request", description = "Model for update {{entity.name}}Request data")
public class {{entity.nameUpper}}Request {
    {{#entityProperties}}
    {{level}} {{type}} {{name}};
    {{/entityProperties}}
}
