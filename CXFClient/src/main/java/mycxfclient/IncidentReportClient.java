package mycxfclient;

import org.apache.camel.example.reportincident.InputReportIncident;
import org.apache.camel.example.reportincident.OutputReportIncident;
import org.apache.camel.example.reportincident.ReportIncidentEndpoint;
import org.apache.camel.example.reportincident.ReportIncidentEndpointService;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by Zoli on 10/11/2016.
 */
public class IncidentReportClient {

    public static void main(String ... args){
        ReportIncidentEndpointService incidentService = new ReportIncidentEndpointService();
        ReportIncidentEndpoint incidentEndpoint = incidentService.getReportIncidentService();

        InputReportIncident iri = new InputReportIncident();
        iri.setDetails("hello");
        iri.setFamilyName("kovacs");


        BindingProvider bp = (BindingProvider)incidentEndpoint;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8900/incident");

        OutputReportIncident ori = incidentEndpoint.reportIncident(iri);

        System.out.println(ori.getCode());
    }
}
