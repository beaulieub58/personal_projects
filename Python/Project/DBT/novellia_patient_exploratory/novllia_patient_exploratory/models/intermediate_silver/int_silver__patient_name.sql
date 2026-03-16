{{ config(materialized= 'incremental',
          schema = 'intermediate',
          unique_key ='patient_id')}}

SELECT
  na.patient_id,
  na.inserted_at,
  na.updated_at,
  na.name_use,
  na.name_family,
  na.name_given,
  na.name_prefix


  FROM
    {{ ref('stg_bronze__patient_events_name') }} na

WHERE 
  na.patient_name_order = 1

{% if is_incremental() %}

  AND na..updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}}) 

{% endif %}
