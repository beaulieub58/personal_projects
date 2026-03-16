{{ config(materialized = 'view')}}

WITH vax_counts AS (
SELECT
  i.immunization_code,
  i.immunization_type,
  COUNT(*) AS completed_vaccines

FROM
  {{ ref('immunization_fct') }} i 

WHERE
  i.immunization_status = 'completed'

GROUP BY 
  i.immunization_code,
  i.immunization_type
),

vax_rankings AS (

SELECT 
  v.immunization_code,
  v.immunization_type,
  v.completed_vaccines,
  RANK() OVER(ORDER BY completed_vaccines DESC) AS vax_volume_order,
  DENSE_RANK() OVER(ORDER BY completed_vaccines DESC) AS dense_vax_volume_order

FROM
  vax_counts v

)

SELECT
  r.immunization_code,
  r.immunization_type,
  r.completed_vaccines,
  r.vax_volume_order AS ranking,
  r.dense_vax_volume_order AS dense_ranking

FROM
  vax_rankings r

WHERE
  r.vax_volume_order <= 5

ORDER BY
  r.completed_vaccines DESC


  
