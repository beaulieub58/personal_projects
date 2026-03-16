{{ config(materialized= 'incremental',
          schema = 'staging')}}
SELECT
  pe.data->>'id' AS patient_id,
  pe.inserted_at,
  pe.updated_at,
  CASE WHEN 
    ext->>'url' LIKE '%adjusted-life-years%' THEN SPLIT_PART(ext->>'url' ,'/', -1)
    ELSE SPLIT_PART(SPLIT_PART(ext->>'url' ,'/', -1),'-',-1) 
  END AS url_text,
  COALESCE(
  ext_ext->'valueCoding'->>'display',
  ext_ext->>'valueString',
  ext->>'valueString',
  ext->>'valueCode',
  ext->>'valueDecimal',
  ext->>'valueAddress'
  ) AS extension_display_name,
  pe.data->>'gender' AS patient_gender,
  DATE(pe.data->>'birthDate') AS patient_birth_date,
  DATE(pe.data->>'deceasedDateTime') AS patient_death_date,
  pe.data->'maritalStatus'->>'text' AS patient_marital_status,
  ROW_NUMBER() OVER(PARTITION BY pe.data->>'id' ORDER BY updated_at DESC) AS latest_patient_record,
  ROW_NUMBER() OVER(PARTITION BY pe.data->>'id',CASE WHEN 
    ext->>'url' LIKE '%adjusted-life-years%' THEN SPLIT_PART(ext->>'url' ,'/', -1)
    ELSE SPLIT_PART(SPLIT_PART(ext->>'url' ,'/', -1),'-',-1) 
  END  ORDER BY updated_at DESC NULLS LAST) AS extended_explosion_oder


  
FROM 
  {{source('individuals','patient_events')}} pe
LEFT JOIN LATERAL
  jsonb_array_elements(pe.data->'extension') AS ext ON TRUE
LEFT JOIN LATERAL
  jsonb_array_elements(ext->'extension') AS ext_ext ON TRUE

{% if is_incremental() %}

WHERE pe.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}})

{% endif %}
