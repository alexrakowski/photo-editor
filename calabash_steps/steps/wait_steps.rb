Then /^I wait for the view with id "([^\"]*)" to show up$/ do |id|
  wait_for(:timeout => 10) { element_exists("* id:'#{id}'") }
end