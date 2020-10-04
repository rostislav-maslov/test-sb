package {{path}}.{{entity.name}}.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "{{entity.nameUpper}}Response", description = "{{entity.nameUpper}} data")
public class {{entity.nameUpper}}Response {
        {{#entityProperties}}
        {{level}} {{type}} {{name}};
        {{/entityProperties}}
}
