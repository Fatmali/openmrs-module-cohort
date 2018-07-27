/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.cohort.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortEncounter;
import org.openmrs.module.cohort.api.CohortService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The main controller.
 */
@Controller
public class CohortEncounterManage {

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/cohort/cohortenc.form", method = RequestMethod.GET)
    public void manage(HttpSession httpSession, HttpServletRequest request, ModelMap model, @RequestParam(required = false, value = "encounterId") Integer id, @ModelAttribute("cohortencounters") CohortEncounter cencounters) {
        CohortEncounter enc = new CohortEncounter();
        CohortService service = Context.getService(CohortService.class);
        if ("search".equals(request.getParameter("search"))) {
            List<CohortEncounter> list1 = service.findCohortEnc(id);
            model.addAttribute("CohortList", list1);
        }
    }
}
