package org.openmrs.module.cohort.web.resource;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.cohort.CohortM;
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

@Resource(name = RestConstants.VERSION_1 + CohortRest.COHORT_NAMESPACE + "/cohort", supportedClass = CohortM.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.10.*, 1.11.*", "1.12.*", "2.0.*", "2.1.*", "2.2.*"})
public class CohortRequestResource extends DataDelegatingCrudResource<CohortM>  {

    protected final Log log = LogFactory.getLog(getClass());
    
    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (Context.isAuthenticated()) {
	        DelegatingResourceDescription description = new DelegatingResourceDescription();
	        
	        if (rep instanceof DefaultRepresentation) {
	            description.addProperty("name");
	            description.addProperty("description");
	            description.addProperty("location");
	            description.addProperty("startDate");
	            description.addProperty("endDate");
	            description.addProperty("cohortType");
	            description.addProperty("cohortProgram");
	            description.addProperty("program");
	            description.addProperty("groupCohort");
	            description.addProperty("uuid");
                description.addProperty("groupNumber");
                description.addProperty("landmark");
                description.addProperty("provider");
	            description.addSelfLink();
	        } 
	        else if (rep instanceof FullRepresentation) {
	            description.addProperty("name");
	            description.addProperty("description");
	            description.addProperty("location");
	            description.addProperty("startDate");
	            description.addProperty("endDate");
	            description.addProperty("cohortType");
	            description.addProperty("cohortProgram");
	            description.addProperty("groupCohort");
                description.addProperty("groupNumber");
                description.addProperty("landmark");
                description.addProperty("provider");
	            description.addProperty("uuid");
                description.addProperty("program");
				description.addProperty("auditInfo");
	            description.addSelfLink();
	        }
	        
	        return description;
		}
		return null;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription description = new DelegatingResourceDescription();
        
        description.addRequiredProperty("name");
        description.addRequiredProperty("groupNumber");
        description.addProperty("landmark");
        description.addProperty("provider");
        description.addProperty("description");
        description.addRequiredProperty("location");
        description.addProperty("startDate");
        description.addProperty("endDate");
        description.addRequiredProperty("cohortType");
        description.addProperty("cohortProgram");
        description.addRequiredProperty("program");
        description.addRequiredProperty("groupCohort");
        
        return description;
    }
    
    @Override
    public DelegatingResourceDescription getUpdatableProperties() throws ResourceDoesNotSupportOperationException {
    	return getCreatableProperties();
    }

    @Override
    public CohortM save(CohortM cohort) {
        return Context.getService(CohortService.class).saveCohort(cohort);
    }

    @Override
    protected void delete(CohortM cohort, String reason, RequestContext request) throws ResponseException {
    	cohort.setVoided(true);
    	cohort.setVoidReason(reason);
        Context.getService(CohortService.class).saveCohort(cohort);
    }

    @Override
    public void purge(CohortM cohort, RequestContext request) throws ResponseException {
        Context.getService(CohortService.class).purgeCohort(cohort);
    }

    @Override
    public CohortM newDelegate() {
        return new CohortM();
    }

    @Override
    public CohortM getByUniqueId(String id) {
        CohortM obj = Context.getService(CohortService.class).getCohortByUuid(id);
        
        if(obj == null) {
        	// TODO add to API
        	// obj = Context.getService(CohortService.class).getCohortByName(id);
        }
        
        return obj;
    }

    @Override
    protected NeedsPaging<CohortM> doGetAll(RequestContext context) throws ResponseException {
    	List<CohortM> cohort = Context.getService(CohortService.class).getAllCohorts();
        return new NeedsPaging<CohortM>(cohort, context);
    }

    @Override
    protected NeedsPaging<CohortM> doSearch(RequestContext context) {
        String name = context.getParameter("q");
        List<CohortM> cohort = Context.getService(CohortService.class).findCohortsMatching(name);
        log.info(cohort);
        log.info("Inside doSearch Cohort");
        System.out.println("COHORT SEARCH");
        System.out.println(cohort);
        return new NeedsPaging<CohortM>(cohort, context);

    }




}
