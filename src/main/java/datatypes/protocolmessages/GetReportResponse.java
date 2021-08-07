package datatypes.protocolmessages;

import datatypes.ReportCV;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetReportResponse extends PacketACK{
    private ReportCV report;

    public GetReportResponse(boolean esito, ReportCV report) {
        super(esito);
        this.report = report;
    }

    public ReportCV getReport() {
        return report;
    }

    public void setReport(ReportCV report) {
        this.report = report;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
