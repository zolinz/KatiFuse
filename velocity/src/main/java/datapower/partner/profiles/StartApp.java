package datapower.partner.profiles;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zoli on 1/03/2017.
 */
public class StartApp {

    public static void main(String[] args)
            throws Exception {

        VelocityEngine ve = new VelocityEngine();
        ve.init();

        String resourcesPath = "velocity/src/main/resources/";
        //String resourcesPath = "";
        //clean directories

        File profileDirs = new File(resourcesPath + "profiles/");
        if (profileDirs.exists()){
            FileUtils.cleanDirectory(profileDirs);
        }




        // Lower environment processing
        Template lowerEnvTemplate = ve.getTemplate(resourcesPath + "templates/exportLower.vm");
        List<String> lowerEnvs = new ArrayList<String>(Arrays.asList("st1", "st2", "st3", "stg", "sit1", "sit2", "sit3", "psvt"));
        for (String environment : lowerEnvs) {
            VelocityContext context = new VelocityContext();

            context.put("environment", environment);
            StringWriter writer = new StringWriter();
            lowerEnvTemplate.merge(context, writer);

            File file = new File(resourcesPath  + environment + "/" + environment + "export.xml");

            FileUtils.writeStringToFile(file, writer.toString());
        }

        // Higher environment processing
        Template higherEnvTemplate = ve.getTemplate(resourcesPath + "templates/exportHigher.vm");
        List<String> higherEnvs = new ArrayList<String>(Arrays.asList("rsvt-dc1", "rsvt-dc2", "psup-dc1", "psup-dc2", "prod-dc1", "prod-dc2"));
        for (String environment : higherEnvs) {
            VelocityContext context = new VelocityContext();

            context.put("environment", environment);
            StringWriter writer = new StringWriter();
            higherEnvTemplate.merge(context, writer);

            File file = new File(resourcesPath  + environment + "/" + environment + "export.xml");

            FileUtils.writeStringToFile(file, writer.toString());
        }






    }
}
