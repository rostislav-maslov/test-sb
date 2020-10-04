package {{path}}.{{entity.name}}.routes;

import {{path}}.base.routes.BaseApiRoutes;

public class {{entity.nameUpper}}ApiRoutes {
    public static final String ROOT = BaseApiRoutes.V1 + "/{{entity.name}}";
    public static final String BY_ID = ROOT + "/{id}";
}
