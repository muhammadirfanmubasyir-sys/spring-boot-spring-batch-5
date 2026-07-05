package com.codesnippet.SpringBatchDemo.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBatchConfigTest {

    @TestConfiguration
    static class TestBatchConfig {
        @Bean
        public JobRepositoryTestUtils jobRepositoryTestUtils(JobRepository jobRepository) {
            return new JobRepositoryTestUtils(jobRepository);
        }

        @Bean
        public JobLauncherTestUtils jobLauncherTestUtils(JobLauncher jobLauncher, Job job,
                JobRepositoryTestUtils jobRepositoryTestUtils) {
            JobLauncherTestUtils utils = new JobLauncherTestUtils();
            utils.setJobLauncher(jobLauncher);
            utils.setJob(job);
            return utils;
        }
    }

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private Job job;

    @Autowired
    private PersonProcessor processor;

    @BeforeEach
    void setUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void testJobIsLoaded() {
        assertNotNull(job);
        assertEquals("importPersons", job.getName());
    }

    @Test
    void testJobLauncherTestUtilsIsLoaded() {
        assertNotNull(jobLauncherTestUtils);
    }

    @Test
    void testJobRepositoryTestUtilsIsLoaded() {
        assertNotNull(jobRepositoryTestUtils);
    }

    @Test
    void testProcessorBeanIsLoaded() {
        assertNotNull(processor);
    }

    @Test
    void testJobExecutionCompletesSuccessfully() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    }

    @Test
    void testJobStepExecutionCount() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        java.util.Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        assertFalse(stepExecutions.isEmpty());

        StepExecution stepExecution = stepExecutions.iterator().next();
        assertEquals("csv-import-step", stepExecution.getStepName());
        assertEquals(1000, stepExecution.getWriteCount());
    }

    @Test
    void testJobCanBeLaunched() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        assertDoesNotThrow(() -> jobLauncherTestUtils.launchJob(jobParameters));
    }

    @Test
    void testJobHasCorrectStepName() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
        assertNotNull(stepExecution.getStartTime());
        assertNotNull(stepExecution.getEndTime());
        assertTrue(stepExecution.getCommitCount() > 0);
    }
}
