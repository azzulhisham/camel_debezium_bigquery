package camelinaction;

import java.util.Map;
import org.apache.camel.builder.RouteBuilder;


public class HelloRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
    	//hello endpoint
//        from("jetty:http://localhost:9972/hello")
//            .transform().simple("Hello from Camel");
        
        //debezium
//        from("debezium-sqlserver:dbz-test-1?offsetStorageFileName=/usr/offset-file-1.dat&databaseHostname=202.71.109.150&databasePort=13342&databaseUser=sa&databasePassword=Enav-DB123Sql456!!!&databaseServerName=enav&databaseDbname=VTS&tableIncludeList=dbo.Events&databaseHistoryFileFilename=/usr/history-file-1.dat")
//        .log("Event received from Debezium : ${body}");
        
        //kafka - Produce
          from("debezium-sqlserver:dbz-test-1?offsetStorageFileName=/usr/offset-file-1.dat&databaseHostname=202.71.109.150&databasePort=13342&databaseUser=sa&databasePassword=Enav-DB123Sql456!!!&databaseServerName=enav&databaseDbname=VTS&tableIncludeList=dbo.Events&databaseHistoryFileFilename=/usr/history-file-1.dat&internalKeyConverter=org.apache.kafka.connect.storage.StringConverter&internalValueConverter=org.apache.kafka.connect.storage.StringConverter")
          .convertBodyTo(Map.class)
          //.transform().jsonpath("$.ShipName")
          .process(new InsertSqlStatement())
          //.to("jdbc:SqlServerDataSource")
          .to("kafka:my_topic?brokers=localhost:9092") 
          .to("google-bigquery-sql://pinc-analytic:INSERT INTO `pinc-analytic.cdc_enav.events` VALUES (${eventId}, '${shipName}', '${timeStamp}')?serviceAccountKey=base64:ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAicGluYy1hbmFs"
          		+ "eXRpYyIsCiAgInByaXZhdGVfa2V5X2lkIjogImZhZDMwMjY3MjFhMDc5ZjEyODYzYzBlNjg3YzI0"
          		+ "YmM5OWQ1ZDVjNDciLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0t"
          		+ "LS1cbk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRQ3Er"
          		+ "V0JpQlhpR25BU3pcbnRiOGo0VUV5NnhuQ2I0RXd1M0JpcnZhRHhSdG9NWXJtRnJpWGU2Q09Mc29L"
          		+ "TEs2cVpwZ3hKUkZCVDhDbHJxNC9cbllXUnExUUczU1BoVU9ZYTQ0R2pGZk5aWFZ5ZHQyVU5qS1Ni"
          		+ "TjE5dW5aZmNkL294QVBKblBTMmxWVjRvSDAxbnVcbjRWWTFTZUtUelgvZlVlV2Z2Si9iZFNrdkZy"
          		+ "cEJjTkVFbEk0WWlNeVlVM2FFSzFBWHZGemRDV1RkMmRORXc5QUxcbnIzSDRaYmpwRmt0enlNM09Z"
          		+ "Q29rTjBST3BDRWlpZjBwNDhXNlh5dzcwK0RVKzNRNkpINVJRK3dkMEdVMG80Sklcbk13ZUFkdXRH"
          		+ "NllJMVJOd1FmQWZGL2F0cEFBcGZENmVBVXM2MEp0djNITFJsOUIyNnpPM2xNTXk0b3UxbDhTVlhc"
          		+ "bkdNQVcrNXF4QWdNQkFBRUNnZ0VBR0dWNEp0U0FBN2tzaEpZby9ZNHRWdjRzRVU0RzRiVEZUeGVI"
          		+ "TG50SU0yQnFcbllQV2FCbWVLRysrNlBhUnRrZndNdUdxQklWQW5mWnpFMlY5a3pFZXp0SHNSa0Rj"
          		+ "YkxiTTd6LzVGdVhYQ3R3NTZcbjZxSGJHeUV6Z3hudUhwWU5YamNuZldoVjBlc3BVMytKaENnY1BJ"
          		+ "TmI5Tm9zWm1uUnVhN1pJcC8vcmV2d3pOaDNcbjJxZ0JwMFBQWlk5RTV6MllKb2tRcEFOYXVuNE5K"
          		+ "aXlYWE4xRmQxR2FzKzBZaHp2aW43OHBuL0ZyZTFMakw0aWtcbnpFSTNIdlJFQjBnS21aSHdsSklo"
          		+ "elZvZ3h5UzlEUnlJaTNqbkRFeHhyNDdjamU3WHFHd2dTVkFJRVpOd2NHK1BcbnpHdXpiTEFFbktz"
          		+ "czJZdHZ0RmQ5UVpXWDhjc2o0L1ZGRmFyL1hWSWVqd0tCZ1FEakdWUnBzUk9WaFYvSlhmRkZcbkdy"
          		+ "TUlMcTJlWStzcXhDMmFPRjhMWDV2ZzZTa0lyd2x4SFF3NmtodGRSS3FYOUl5Z1lFY2RvRGx4UkJP"
          		+ "ZnNUZlpcbjFwRkZUUXhNZVBReG1aRTdZb2RVblFsM3NHRWlCMXJ4NXFzbmQ1RGw2MzdybXJWU0pH"
          		+ "NUl3U3V5bGhRa1RqQnpcbkxtdERlYmFzdzhjcURtOVgyckJXTEtVNnh3S0JnUURBdTQyTkVPenpR"
          		+ "Vnl6NGlUN0NleWZlMTlGMFE5bEtxWXVcbnh3dllFSjh4UlFxTnVndlB1OVVIQ3NxdW5LQ3o2aVVN"
          		+ "SzRUd2MweFErQ2ppMUN0Wjd2M2FXbWtJVGxPUThpWmRcbnVsMUxuNUIrQ0JrSmprNFFrRW1iODdI"
          		+ "SnJQeVJrWlhheGR6SHY2cnVIc0FCRUlzSW1peGdoS3FiRFhQc2JlL0xcbm9FdHNJMFBHeHdLQmdB"
          		+ "ZzJhRWtTTUVVZDk2TE9sT1VNM0NDVm40MjB5MUpXbWRlVDVrd0EveUFaRG5UVDc2RjVcbmpYR295"
          		+ "dmlLZlNqVm5TaEhrd3JOT29hUUVhMmF1UXhWcUJjN2ZCQmY1MlRMeXVhODMydjk2ZE84T3ljQjRS"
          		+ "WVNcblkyb2Y5c3Q1d3JNK2V3UW5jUjJNYnNvY2gwbncwZXU0YUFTZXFDNkRQZmFWWXcxb1NTdzBu"
          		+ "UjBOQW9HQkFJMXlcbm1JWnBYNTEvNE90Y0UwdnlrcWpSUUxEYXAvUFZnelZkZkFITTRTaWx4UU1U"
          		+ "QWN3cHpFUm8xU3VsWkFQbTJkMitcbnFzaFZ0akxPamlNN0Y3M2ZPb3RnUXltSFlxbEVMb2duV0lW"
          		+ "QnpDZlRCWjBQeFpjK2JlZDR5SWg4M3JodklvMzNcbkhQdTBYVng1VU96M0dUTHRBNWlxaFJOYW9q"
          		+ "Tm8wODZjQy82SE94ek5Bb0dCQUtsVjlVdzFValRCUXRuNjNOT1dcbnBkZExhUnVXUzN3ZzJvS1FS"
          		+ "Z1BoUDFNVUsyNDlDckp0K3JvaGxJbHFjYmRVcDVHd1lZck5FN2w0aFhnczJqQW9cbmxsb21QWXp6"
          		+ "WmV0YzI5Wm1tMmlzbm9IcEtTS0RXbVJEbGpJV0FhdS84MmJUVkFkR3ZOUG1zeXFYRm5jQ2VDMFdc"
          		+ "bkxhZHhyMHBhSW1ZYXhLdXV0a29pQTVRb1xuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwK"
          		+ "ICAiY2xpZW50X2VtYWlsIjogImJpZ3F1ZXJ5QHBpbmMtYW5hbHl0aWMuaWFtLmdzZXJ2aWNlYWNj"
          		+ "b3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTExNDM3NjIxODM5MjM5Njg5OTQ5IiwKICAiYXV0"
          		+ "aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0"
          		+ "b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRo"
          		+ "X3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1"
          		+ "dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29v"
          		+ "Z2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9iaWdxdWVyeSU0MHBpbmMtYW5hbHl0"
          		+ "aWMuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iCn0K");
                
        
        //kafka - consume  
          from("kafka:my_topic?brokers=localhost:9092")
          .log("${body}");
    }
}
