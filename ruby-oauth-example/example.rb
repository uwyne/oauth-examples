require 'oauth2'
client = OAuth2::Client.new('client_id', 'client_secret', :site => 'https://clover.com/', :token_method  => :get)

oauth_endpoint = client.auth_code.authorize_url(:redirect_uri => 'http://www.example.com/oauth_callback') 
puts oauth_endpoint
#redirect a merchant to this URL.

puts "Enter the authcode:"
code = gets.chomp
token = client.auth_code.get_token(code)
response = token.get('/v2/inventory/items')
