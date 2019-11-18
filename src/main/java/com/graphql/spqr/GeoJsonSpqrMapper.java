package com.graphql.spqr;

import graphql.schema.GraphQLInputObjectType;
import io.leangen.geantyref.GenericTypeReflector;
import io.leangen.graphql.generator.BuildContext;
import io.leangen.graphql.generator.OperationMapper;
import io.leangen.graphql.generator.mapping.common.ObjectTypeMapper;
import io.leangen.graphql.metadata.InputField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.lang.reflect.AnnotatedType;
import java.util.HashSet;
import java.util.Set;

import static graphql.schema.GraphQLInputObjectType.newInputObject;

@Slf4j
public class GeoJsonSpqrMapper extends ObjectTypeMapper {
    @Override
    public GraphQLInputObjectType toGraphQLInputType(String typeName, AnnotatedType javaType, OperationMapper operationMapper, BuildContext buildContext) {
        GraphQLInputObjectType.Builder typeBuilder = newInputObject()
                .name(typeName)
                .description(buildContext.typeInfoGenerator.generateInputTypeDescription(javaType, buildContext.messageBundle));

        final Set<InputField> fields = new HashSet<>();
        fields.add(new InputField("geo", "GeoJson point", GenericTypeReflector.annotate(GeoJsonPoint.class), GenericTypeReflector.annotate(GeoJsonPoint.class), new GeoJsonPoint(0, 0), null));
        fields.forEach(field -> typeBuilder.field(operationMapper.toGraphQLInputField(field, buildContext)));

        return typeBuilder.build();
    }

    @Override
    public boolean supports(AnnotatedType type) {
        return GenericTypeReflector.isSuperType(GeoJson.class, type.getType());
    }
}
