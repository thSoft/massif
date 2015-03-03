package hu.bme.mit.massif.models.simulink.util.util;

import com.google.common.collect.Sets;
import hu.bme.mit.massif.models.simulink.util.BusSpecificationInPortToOutPortMatch;
import hu.bme.mit.massif.models.simulink.util.BusSpecificationInPortToOutPortMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeUnary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;

/**
 * A pattern-specific query specification that can instantiate BusSpecificationInPortToOutPortMatcher in a type-safe way.
 * 
 * @see BusSpecificationInPortToOutPortMatcher
 * @see BusSpecificationInPortToOutPortMatch
 * 
 */
@SuppressWarnings("all")
public final class BusSpecificationInPortToOutPortQuerySpecification extends BaseGeneratedEMFQuerySpecification<BusSpecificationInPortToOutPortMatcher> {
  private BusSpecificationInPortToOutPortQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static BusSpecificationInPortToOutPortQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected BusSpecificationInPortToOutPortMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return BusSpecificationInPortToOutPortMatcher.on(engine);
  }
  
  @Override
  public BusSpecificationInPortToOutPortMatch newEmptyMatch() {
    return BusSpecificationInPortToOutPortMatch.newEmptyMatch();
  }
  
  @Override
  public BusSpecificationInPortToOutPortMatch newMatch(final Object... parameters) {
    return BusSpecificationInPortToOutPortMatch.newMatch((hu.bme.mit.massif.simulink.InPort) parameters[0], (hu.bme.mit.massif.simulink.OutPort) parameters[1]);
  }
  
  private static class LazyHolder {
    private final static BusSpecificationInPortToOutPortQuerySpecification INSTANCE = make();
    
    public static BusSpecificationInPortToOutPortQuerySpecification make() {
      return new BusSpecificationInPortToOutPortQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static BusSpecificationInPortToOutPortQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.massif.models.simulink.util.busSpecificationInPortToOutPort";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("inportOfBS","outPortOfBS");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("inportOfBS", "hu.bme.mit.massif.simulink.InPort"),new PParameter("outPortOfBS", "hu.bme.mit.massif.simulink.OutPort"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_inportOfBS = body.getOrCreateVariableByName("inportOfBS");
      	PVariable var_outPortOfBS = body.getOrCreateVariableByName("outPortOfBS");
      	PVariable var_BS = body.getOrCreateVariableByName("BS");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_inportOfBS, "inportOfBS"),
      				
      		new ExportedParameter(body, var_outPortOfBS, "outPortOfBS")
      	));
      	new TypeUnary(body, var_inportOfBS, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "InPort"), "http://hu.bme.mit.massif/simulink/1.0/InPort");
      	new TypeUnary(body, var_outPortOfBS, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "OutPort"), "http://hu.bme.mit.massif/simulink/1.0/OutPort");
      	new TypeUnary(body, var_BS, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "BusSpecification"), "http://hu.bme.mit.massif/simulink/1.0/BusSpecification");
      	new TypeBinary(body, CONTEXT, var_BS, var_inportOfBS, getFeatureLiteral("http://hu.bme.mit.massif/simulink/1.0", "Block", "ports"), "http://hu.bme.mit.massif/simulink/1.0/Block.ports");
      	new TypeBinary(body, CONTEXT, var_BS, var_outPortOfBS, getFeatureLiteral("http://hu.bme.mit.massif/simulink/1.0", "Block", "ports"), "http://hu.bme.mit.massif/simulink/1.0/Block.ports");
      	bodies.add(body);
      }
      	// to silence compiler error
      	if (false) throw new IncQueryException("Never", "happens");
      } catch (IncQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}