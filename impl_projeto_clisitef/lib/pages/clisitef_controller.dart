
import 'package:impl_projeto_clisitef/pages/clisitef.dart';
import 'package:impl_projeto_clisitef/services/clisitef/model/transaction_events.dart';
import 'package:impl_projeto_clisitef/services/clisitef/pdv/clisitef_pdv.dart';

class MainController {
  final clisitefPlugin = CliSitef.instance;
  final bool isSimulated = false;

  late CliSiTefPDV pdv;

  TransactionEvents transactionStatus = TransactionEvents.unknown;
  List<String> dataReceived = [];

  String pinPadInfo = '';
  String lastTitle = '';
  String lastMsgCustomer = '';
  String lastMsgCashier = '';
  String lastMsgCashierCustomer = '';

  bool showAbortButton = false;
  bool abortTransaction = false;
}
