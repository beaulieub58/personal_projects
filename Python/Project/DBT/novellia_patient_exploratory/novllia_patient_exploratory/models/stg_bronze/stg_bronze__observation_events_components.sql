{{ config(materialized= 'incremental',
          schema = 'staging')}}
SELECT
  oe.data->>'id' AS observation_id,
  oe.inserted_at,
  oe.updated_at,
  oe.data->'category'->0->'coding'->0->>'code' AS category_code,
  oe.data->'category'->0->'coding'->0->>'display' AS category_name,
  components->'code'->'coding'->0->>'code' AS component_code,
  components->'code'->'coding'->0->>'display' AS component_display_name,
  CAST(components->'valueQuantity'->>'value' AS FLOAT) AS component_value,
  components->'valueQuantity'->>'unit' AS component_unit,
  components->'valueCodeableConcept'->'coding'->0->>'code' AS component_codeable_concept_code,
  components->'valueCodeableConcept'->'coding'->0->>'display' AS component_odeable_concept_display

FROM
  {{ source('diagnostics','observation_events')}} oe
CROSS JOIN LATERAL 
  jsonb_array_elements(oe.data->'component') AS components
  
{% if is_incremental() %}

WHERE oe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}
