{{config(materialized = 'view')}}

SELECT
  p.patient_id,
  CASE 
    WHEN p.birth_city != p.patient_city THEN 'Patient was born in a city with a different name than their current city.' 
    ELSE 'The name of the city where patient was born is the same as their current city!'
  END AS fun_fact_one,
  'The patient is: ' || (CURRENT_DATE - p.patient_birth_date)::text || ' days old.' AS fun_fact_two
  
FROM
  {{ ref('patient_dim')}} p