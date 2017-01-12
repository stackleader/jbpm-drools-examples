/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jeckste.jbpmdrools.examples.agroupinrflow;


import java.util.HashMap;
import java.util.Map;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jeckste
 */
public class AgendaGroupsInRuleFlowTest extends JbpmJUnitBaseTestCase {
    
    Logger LOG =  LoggerFactory.getLogger(AgendaGroupsInRuleFlowTest.class);

    @Test
    public void testProcess() {
        RuntimeManager manager = createManager();
        KieSession ksession = manager.getRuntimeEngine(null).getKieSession();
        ksession.setGlobal("LOG", LOG);
        
        Map<String, Object> params = new HashMap<>();
        ksession.startProcess("com.sample.agroupinaflow", params);
    }

    private static RuntimeManager createManager() {
        RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get().newEmptyBuilder()
                .addAsset(KieServices.Factory.get().getResources()
                        .newClassPathResource("agroupinrflow/example.bpmn"), ResourceType.BPMN2)
                .addAsset(KieServices.Factory.get().getResources()
                        .newClassPathResource("agroupinrflow/example.drl"), ResourceType.DRL)
                .get();
        return RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
    }

}
