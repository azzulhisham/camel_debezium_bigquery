package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;


public class HelloCamel {

    public static void main(String[] args) throws Exception {
    	
//    	MysqlDataSource ds = new MysqlDataSource();
//    	ds.setURL("jdbc:mysql://localhost:3306/pinc");
//    	ds.setUser("root");
//        ds.setPassword("Az@HoePinc0615");

    	SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("Az@HoePinc0615");
        ds.setServerName("localhost");
        ds.setPortNumber(1433);
        ds.setDatabaseName("Pinc");
        
        SimpleRegistry registry = new SimpleRegistry();
        registry.bind("SqlServerDataSource", ds);
    	
        CamelContext context = new DefaultCamelContext(registry);
        context.addRoutes(new HelloRoute());
        context.start();

        // keep the JVM running (a bit of a hack)
        Thread.sleep(Integer.MAX_VALUE);
    }

}

