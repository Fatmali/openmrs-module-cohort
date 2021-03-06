package org.openmrs.module.cohort.web.resource;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortProgram;
import org.openmrs.module.cohort.api.CohortService;
import org.openmrs.module.cohort.rest.v1_0.resource.CohortRest;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/cohortprogram", supportedClass = CohortProgram.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.10.*, 1.11.*", "1.12.*", "2.0.*", "2.1.*", "2.2.*"})
public class CohortProgramRequestResource extends DataDelegatingCrudResource<CohortProgram> {

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {

        DelegatingResourceDescription description = null;

        if (Context.isAuthenticated()) {
            description = new DelegatingResourceDescription();
            if (rep instanceof DefaultRepresentation) {
                description.addProperty("name");
                description.addProperty("description");
                description.addProperty("uuid");
                description.addSelfLink();
            } 
            else if (rep instanceof FullRepresentation) {
                description.addProperty("name");
                description.addProperty("description");
                description.addProperty("uuid");
                description.addProperty("auditInfo");
				description.addSelfLink();
            }
        }
        return description;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        description.addRequiredProperty("name");
        description.addProperty("description");
        return description;
    }
    
    @Override
    public DelegatingResourceDescription getUpdatableProperties() throws ResourceDoesNotSupportOperationException {
    	return getCreatableProperties();
    }

    @Override
    public CohortProgram save(CohortProgram cohortProgram) {
        return Context.getService(CohortService.class).saveCohortProgram(cohortProgram);
    }

    @Override
    protected void delete(CohortProgram cohortProgram, String reason, RequestContext context) throws ResponseException {
    	cohortProgram.setVoided(true);
    	cohortProgram.setVoidReason(reason);
    	Context.getService(CohortService.class).saveCohortProgram(cohortProgram);
    }

    @Override
    public void purge(CohortProgram cohortProgram, RequestContext context) throws ResponseException {
    	Context.getService(CohortService.class).purgeCohortProgram(cohortProgram);
    }

    @Override
    public CohortProgram newDelegate() {
        return new CohortProgram();
    }

    @Override
    public CohortProgram getByUniqueId(String uuid) {
        CohortProgram obj = Context.getService(CohortService.class).getCohortProgramByUuid(uuid);
        if(obj == null) {
        	obj = Context.getService(CohortService.class).getCohortProgramByName(uuid);
        }
		return obj;
    }

    @Override
    protected PageableResult doGetAll(RequestContext context) throws ResponseException {
    	List<CohortProgram> list = Context.getService(CohortService.class).getAllCohortPrograms();
    	return new NeedsPaging<CohortProgram>(list, context);
    }
}
