app.root.onget=function(request)
{
  print('Hi');
  print('Bye');
  var n = "Hello";
  request.respond(69, n);
}
