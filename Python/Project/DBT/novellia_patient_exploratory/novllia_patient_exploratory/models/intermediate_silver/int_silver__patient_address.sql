{{ config(materialized= 'incremental',
          schema = 'intermediate',
          unique_key ='patient_id')}}

SELECT
  ea.patient_id,
  ea.inserted_at,
  ea.updated_at,
  ea.patient_address_line,
  ea.patient_city,
  ea.patient_state,
  ea.patient_zip,
  ea.patient_country


  FROM
    {{ ref('stg_bronze__patient_events_address') }} ea

WHERE 
  ea.patient_address_order = 1

{% if is_incremental() %}

  AND ea.updated_at > (SELECT COALESCE(MAX(updated_at), '2000-01-01' )FROM {{this}}) 

{% endif %}
