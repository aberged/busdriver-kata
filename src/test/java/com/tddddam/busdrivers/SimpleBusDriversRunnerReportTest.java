package com.tddddam.busdrivers;

import com.tddddam.busdrivers.reporters.SimpleBusDriversRunnerReport;
import org.junit.Assert;
import org.junit.Test;

public class SimpleBusDriversRunnerReportTest {

    @Test
    public void cant_be_instantiated_without_BusDriversRunner_injected(){
        new SimpleBusDriversRunnerReport(new BusDriversRunner());
    }

    @Test
    public void simple_report_implementation_makes_string_output(){
        Assert.assertTrue((new SimpleBusDriversRunnerReport(
                                new BusDriversRunner()
                            ).getReport()) instanceof String
        );
    }

    @Test
    public void simple_report_output_content_is_configurable(){
        //todo
    }

}
