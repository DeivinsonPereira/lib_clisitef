library clisitef;

import 'dart:io';

import 'package:flutter/services.dart';
import 'package:impl_projeto_clisitef/android/clisitef_android.dart';
import 'package:impl_projeto_clisitef/services/clisitef/clisitef_sdk.dart';

class CliSitef {
  CliSitef._();

  static CliSiTefSDK get instance => Platform.isAndroid
      ? CliSiTefAndroid()
      : throw PlatformException(
          code: 'NotSupported',
          message: 'This library only supports Android applications');
}
