import 'package:flutter/services.dart';

import 'model/clisitef_data.dart';
import 'model/pinpad_events.dart';
import 'model/pinpad_information.dart';
import 'model/tipo_pinpad.dart';
import 'model/transaction_events.dart';

typedef TransactionEvent2Void = void Function(TransactionEvents,
    {PlatformException? exception});

typedef PinPadEvent2Void = void Function(PinPadEvents,
    {PlatformException? exception});

typedef Data2Void = void Function(CliSiTefData);

abstract class CliSiTefSDK {
  Future<bool> abortTransaction({int continua = 0});

  Future<bool> continueTransaction(String data);

  Future<bool> configure(
    String enderecoSitef,
    String codigoLoja,
    String numeroTerminal,
    String cnpjAutomacao,
    String cnpjLoja,
    TipoPinPad tipoPinPad, {
    String parametrosAdicionais = '',
  });

  Future<bool> finishTransaction(
      bool confirma, String cupomFiscal, DateTime dataFiscal);

  Future<bool> finishLastTransaction(bool confirma);
//https://dev.softwareexpress.com.br/docs/sitef-interface-simplificada/tabela_de_codigos_meios_pagamento/ - Muito grande para usar ENUM
  Future<bool> startTransaction(
    int modalidade,
    double valor,
    String cupomFiscal,
    DateTime dataFiscal,
    String operador, {
    String restricoes = '',
  });

  Future<int?> getTotalPendingTransactions(
      DateTime dataFiscal, String cupomFiscal);

  Future<PinPadInformation> getPinpadInformation();

  Future<int> getPinPadYesOrNo(String message);

  Future<int?> setPinpadDisplayMessage(String message);

  void setEventHandler(TransactionEvent2Void? transactionEventHandler,
      PinPadEvent2Void? pinPadEventHandler);

  void setDataHandler(Data2Void listener);
}