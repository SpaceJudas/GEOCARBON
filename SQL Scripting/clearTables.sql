TRUNCATE TABLE GCDefault_Arrays
GO
TRUNCATE TABLE GCDefault_Constants
GO
TRUNCATE TABLE GCRun_Arrays
GO

ALTER TABLE GCRun_Arrays
    DROP FOREIGN KEY fk_run
GO

TRUNCATE TABLE GCRun_Constants
GO

ALTER TABLE GCRun_Arrays
    ADD CONSTRAINT fk_run FOREIGN KEY (run_id) REFERENCES GCRun_Constants(run_id)
GO