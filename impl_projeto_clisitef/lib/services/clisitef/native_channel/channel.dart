// ignore_for_file: constant_identifier_names

import 'package:flutter/services.dart';

class Channel {
   final MethodChannel _methodChannel = const MethodChannel('com.example.impl_projeto_clisitef');
   final EventChannel _eventChannel = const EventChannel('com.example.impl_projeto_clisitef/events');
   final EventChannel _dataChannel = const EventChannel('com.example.impl_projeto_clisitef/events/data');

   MethodChannel get methodChannel => _methodChannel;
   EventChannel get eventChannel => _eventChannel;
   EventChannel get dataChannel => _dataChannel;

   
}
