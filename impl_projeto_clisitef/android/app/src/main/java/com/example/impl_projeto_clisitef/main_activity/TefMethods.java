package com.example.impl_projeto_clisitef.main_activity;

import br.com.softwareexpress.sitef.android.ICliSiTefListener;

public class TefMethods extends SiTefClient{

    private int idConfig = 0;

    // Method to abort a transaction
    public void abortTransaction(int continueStatus) {
        switch (cliSiTef.abortTransaction(continueStatus)) {
            case 0:
                success(true);
                break;
            case -1:
                success(false);
                break;
            default:
                success(false); // Handle the default case or log an unexpected behavior
                break;
        }
    }

    // Method to configure the Tef
    public void configure(String enderecoSiTef, String codigoLoja, String numeroTerminal, String parametrosAdicionais) {
        try {
            idConfig = cliSiTef.configure(enderecoSiTef, codigoLoja, numeroTerminal, parametrosAdicionais);
            switch (idConfig) {
                case 0:
                    success(true);
                    break;
                case 1:
                    error(Integer.toString(idConfig), "Invalid enderecoSiTef or not reachable");
                    break;
                case 2:
                    error(Integer.toString(idConfig), "Invalid codigoLoja");
                    break;
                case 3:
                    error(Integer.toString(idConfig), "Invalid numeroTerminal");
                    break;
                case 6:
                    error(Integer.toString(idConfig), "TCP/IP init error");
                    break;
                case 7:
                    error(Integer.toString(idConfig), "Memory overflow");
                    break;
                case 8:
                    error(Integer.toString(idConfig), "CliSiTef not found");
                    break;
                case 9:
                    error(Integer.toString(idConfig), "SiTef Server config exceeded");
                    break;
                case 10:
                    error(Integer.toString(idConfig), "Could not access CliSiTef dir", "Check for permissions");
                    break;
                case 11:
                    error(Integer.toString(idConfig), "Invalid data");
                    break;
                case 12:
                    error(Integer.toString(idConfig), "Safe mode not enabled");
                    break;
                case 13:
                    error(Integer.toString(idConfig), "Invalid DLL path");
                    break;
                default:
                    error(Integer.toString(idConfig), "Unknown configuration error");
                    break;
            }
        } catch (Error e) {
            error("configure", e.toString());
        }
    }

    public void continueTransaction(String data) {
        switch (cliSiTef.continueTransaction(data)) {
            case 0:
                success(true);
                break;
            case -1:
                success(false);
                break;
            default:
                success(false); // Trate casos não especificados se necessário
                break;
        }
    }

    public void finishTransaction(int confirm, String taxInvoiceNumber, String taxInvoiceDate, String taxInvoiceTime) {
        try {
            processTransactionStatus(cliSiTef.finishTransaction(confirm, taxInvoiceNumber, taxInvoiceDate, taxInvoiceTime, ""));
        } catch (Exception e) {
            success(false);
        }
    }

    public void startTransaction(ICliSiTefListener listener, int functionId, String trnAmount, String taxInvoiceNumber, String taxInvoiceDate, String taxInvoiceTime, String cashierOperator, String restrictions) {
        processTransactionStatus(cliSiTef.startTransaction(listener, functionId, trnAmount, taxInvoiceNumber, taxInvoiceDate, taxInvoiceTime, cashierOperator, restrictions));
    }

    public void getQttPendingTransactions(String dataFiscal, String cupomFiscal) {
        try {
            success(cliSiTef.getQttPendingTransactions(dataFiscal, cupomFiscal));
        } catch (Exception e) {
            error("getQttPendingTransactions", e.toString());
        }
    }

    public void finishLastTransaction(int confirm) {
        try {
            processTransactionStatus(cliSiTef.finishTransaction(confirm));
        } catch (Exception e) {
            success(false);
        }
    }

    private void processTransactionStatus(int transactionStatus) {
        switch (transactionStatus) {
            case 10000:
            case 0:
                success(true);
                break;
            case -1:
                error(Integer.toString(transactionStatus), "Module not initialized");
                break;
            case -2:
                error(Integer.toString(transactionStatus), "Operation aborted by the operator");
                break;
            case -3:
                error(Integer.toString(transactionStatus), "Invalid functionId");
                break;
            case -4:
                error(Integer.toString(transactionStatus), "Low memory to run the function");
                break;
            case -5:
                error(Integer.toString(transactionStatus), "No communication with SiTef server");
                break;
            case -6:
                error(Integer.toString(transactionStatus), "Operation aborted by the user");
                break;
            default:
                if (transactionStatus >= 1) {
                    error(Integer.toString(transactionStatus), "Denied by the authorizer");
                } else if (transactionStatus <= -7) {
                    error(Integer.toString(transactionStatus), "Errors internally detected by the routine");
                }
                break;
        }
    }
}
