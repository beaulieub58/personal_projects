
Initial Research And Documentation: 

--- INTRO ---

The intended purpose of this ReadMe is to inform the reader of my approach to this case study and prepare junior/other senior engineers or analysts on the approach, should they want to replicate on their own PSQL/DBT instance.

I treated this assignment as an opportunity to replicate approaches I have seen in my professional career, namely working with data on a source db - usually a PSQL or MySQL db. Traditionally, our teams would use a cloud environment for all dbt modeling purposes. Said differently, PSQL would be treated as the storage for application events while third party/vendor data would go straight to Snowflake or BigQuery in a sourcing db. In this instance, I am treating a distinct fhir database as that source, and doing dbt modeling downstream, within PSQL, that would otherwise be configured and executed in a cloud data warehouse. The locations for this modeled data are in the public_staging (bronze), public_intermediate (silver), and public (gold/mart) schemas.

The general approach is explained below, but summarized as follows:
	1. Ingest raw csv's from github repo in to local PSQL instance, within the "fhir" database and assign to schemas determined by FHIR documentation (as close as I could)
	2. Create a bronze model folder, which extracts jsonb columns ingested in to PSQL and explodes nested jsonb objects where needed to answer questions posed by case study
		-- The models will be incremental in the event the datasets increase going forward, to optimize performance
	3. Dedupe, unpivot, and pivot tables with unique key configurations (where needed), and prepare for mart objects
	4. Create generalized data mart objects that would be useful for analytical querying and business intelligence tooling. Additionally, bespoke views will be created to answer the questions posed by the case study.

--- WORKING APPROACH --- 

Please see below a list of table and schema pairs that were matched by referencing FHIR documentation at this location: https://www.hl7.org/fhir/resourcelist.html

For future reference, all new tables should attempt to match documented object categories. 

Please note, all tables placed under the Clinical - Summary object have been subsequently denoted as clinicial to avoid conflating summary tables/views with 


1. Identify table-schema mappings (FHIR based documentation):
	1. Procedure - clinical
		https://www.hl7.org/fhir/procedure.html
	2. PractitionerRole - individuals
		https://www.hl7.org/fhir/practitionerrole.html
	3. Practitioner - individuals 
		https://www.hl7.org/fhir/practitioner.html
	4. Patient - individuals (NEEDED)
		https://www.hl7.org/fhir/patient.html
	5. Organization - entities 
		https://www.hl7.org/fhir/organization.html
	6. Observation - diagnositcs (NEEDED)
		https://www.hl7.org/fhir/observation.html
	7. MedicationRequest - medications 
		https://www.hl7.org/fhir/medicationrequest.html
	8. Location - entities 
		https://www.hl7.org/fhir/location.html
	9. Immunization - medications (NEEDED)
		https://www.hl7.org/fhir/immunization.html
	10. Encounter - management 
		https://www.hl7.org/fhir/encounter.html
	11. DocumentReference - documents
		https://www.hl7.org/fhir/documentreference.html
	12. DiagnosticReport - diagnostics (NEEDED)
		https://www.hl7.org/fhir/diagnosticreport.html
	13. Device - entities
		https://www.hl7.org/fhir/device.html
	14. Condition - clinical
		https://www.hl7.org/fhir/condition.html
	15. Allergy Intolerance - clinical
		https://www.hl7.org/fhir/allergyintolerance.html


2. Building Postgres DB and schema mapping based on documentation

	1. CREATE DATABASE fhir
	2. \c fhir 
	3. CREATE SCHEMA (schema name based on mapping above)
	4. CREATE TABLE (data jsonb)
	5. Assign admin role to myself for dropping tables/scehmas/and altering tables with inserted at and updated at columns

3. Load local files in to postgres instance to replicate streaming/api load (needed and for fun tables)
	1. \copy clinical.procedure_events(data) FROM '~/Downloads/sample_data_fhir/Condition.000.ndjson'
	2. Ingestion issue identified with source Patient.000.ndjson file. Text URL are not escaped and causing disruption on insertion in to postgres db. Need to be cleaned up before insertion

4. Configure a virtual environment to install dbt-core and dbt-postgres packages

5. Activate virtual environment for dbt by locating venv file path and running:
  source bin/activate -> dbt debug (check your connection)

6. Run some test queries in PSQL to understand json structure and start modeling data.

7. Noted!
	1. Need time to identify guaranteed nested json elements. CROSS JOIN VS LEFT JOIN ON TRUE. This pops up in the diagnosticReport file (results not present for non-lab reports)
	2. As a result of this, and to keep tables from exploding for more complicated parsing downstream, I will dimensionalize the larger, nested objects (patient, diagnostics)

8. Build bronze evironment
	1. Since raw data has been ingested to PSQL source, extract relevant JSON fields and explode neccessary nested json items - incremental
	2. Cast data types appropriately, ie. extract json text to dates and float values where appropriate.

9. Build silver environment
    1. Largely intended to dedupe bronze enviromnent if needed and assign unique key values where approrpiate to inform merge ops on incremental models
    2. Some additional preparation for mart data

10. Build mart environment
	1. surface fact, child and dim tables to prompt answers to the questions posed by the case study
	2. Build views that answer each question specifically. Please note, I would not take this approach if had a business intelligence tool that can surface the mart data holistically. The views are simply intended to ease readers in to the answers without having to go searching for them - strictly intended to answer questions neatly and quickly and determine what the logic is and every improvement

11. Docoment models in schema files
	1. I will be documenting the models in a time-boxed format.

12. Some custom tests have been developed to validate some numerical values in the mart views. These are located in the test directory.

13. Once schema file on gold/mart models are complete test your model with dbt build --full-refresh

--- TRADEOFFS ---

1. Instead of seeding the provided csv files directly in dbt, I sourced them from a local PSQL instance, which added time to my modeling process and added time in configuring the instance.

2. Instead of exploding rows via cross joining all at once in the bronze layer, I evaluated through trial and error how consequential it would be by cross joining nested json elements. When tables started blowing up, I would break the source tables down in to separate components that could be modeled further downstream. This added to the number of models needed, which can be cumbersome, but these sub-models could have potential to be useful in other applications which ascribes value as standalones

3. There were multiple instances, due to using the small data set, where I directly indexed jsonb elements that contained lists at position 0, allowing for easier access to objects without having to cross join or left join lateral. This works on the assumption that there is only 1 item contained in each of the nested list objects. Model tests passed where appropriate, but a break in this assumption would require a complete rework of the upstream layer.

4. I am surfacing fields in the patient dim table, within the mart layer, that would be subject to scrutiny given their sensitivity. There would have to be enhanced column masking completed on this table - with permissions for viewing limited to specific accounts and service accounts only.

5. I am doing some row unpivotting -> pivotting in 2 separate models within the silver to account for coalesced column values and labels, especially in the patient data. If the number of column values increases, the query will have to account for those new column values, so pivotted rows are not missed downstream. There could be some alerting introduced for this

6. I am using views to communicate answers to questions posed by the case. It is generaly bad practice to have a view list one row and one column of data. This is not something I would do in production, only in practice.

7. If more time allotted, it would have been fun to explore more custom testing (they're called assertion files in GCP Dataform)

--- LOOKING FORWARD (Additional FHIR Resources) ---

1. I would rather use the approach I did of storing the source data in psotgres tables ON THE ASSUMPTION that a direct intgregation could be created with FHIR data - to avoid having to replace seed csv's constantly

2. It would potentially be wise to use the Polars package to quickly create dataframes from the json events, and explode/unnest nested items quickly with the assistance of a Rust API.

3. The possibilities for analytics are endless for this data when every resource is considered. A semantic layer and dbt macros would be arduous/laborious to maintain, but would be able to add some clarity to the variety of metrics, measures, and field definitions that would eventually propagate to a business intelligence/data viz tool.


