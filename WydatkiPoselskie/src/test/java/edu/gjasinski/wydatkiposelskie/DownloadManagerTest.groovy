package test.java.edu.gjasinski.wydatkiposelskie

import main.java.edu.gjasinski.wydatkiposelskie.DownloadManager
import spock.lang.Specification


class DownloadManagerTest extends Specification {
    def "method downloadJson should download some data"(){
        setup:
        DownloadManager downloadManager = new DownloadManager()
        URL url = new URL("https://www.google.pl")

        when:
        String data = downloadManager.downloadJson(url)

        then:
        data.length() > 0
    }

    def "method downloadJson should throw IOException"(){
        setup:
        DownloadManager downloadManager = new DownloadManager()
        URL url = new URL("https://niematakiejstrony.agh.edu.cs.arizona.pl")

        when:
        String data = downloadManager.downloadJson(url)

        then:
        thrown(IOException)
    }

    def "method downloadPoliticianTravelsAndExpensesJson should download some data"(){
        setup:
        DownloadManager downloadManager = new DownloadManager()
        int id = 5

        when:
        String data = downloadManager.downloadPoliticianTravelsAndExpensesJson(id)

        then:
        data.length() > 0
    }
}
