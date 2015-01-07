Then /^I see the text "([^\"]*)"$/ do |text|
  wait_for_text(text, timeout: 10)
end

Then /^I see "([^\"]*)"$/ do |text|
  wait_for_text(text, timeout: 10)
end

Then /^I should see "([^\"]*)"$/ do |text|
  wait_for_text(text, timeout: 10)
end

Then /^I should see text containing "([^\"]*)"$/ do |text|
  wait_for_text(text, timeout: 10)
end

Then /^I see element with id "([^\"]*)"$/ do |id|
  wait_for_element_exists("* id:'#{id}'")
end

Then /^I should see element with id "([^\"]*)"$/ do |id|
  wait_for_element_exists("* id:'#{id}'")
end

Then /^I should see at least (\d+) "([^\"]*)" elements in view with id "([^\"]*)"$/ do |count, viewtype, id|
  no_of_found_elements = query("* id:'#{id}' child #{viewtype}").size
  raise "Not enough elements found! (#{no_of_found_elements}, expected:#{count}" if no_of_found_elements < count.to_i
end


Then /^I should not see "([^\"]*)"$/ do |text|
  wait_for_text_to_disappear(text, timeout: 10)
end

Then /^I don't see the text "([^\"]*)"$/ do |text|
  wait_for_text_to_disappear(text, timeout: 10)
end

Then /^I don't see "([^\"]*)"$/ do |text|
  wait_for_text_to_disappear(text, timeout: 10)
end
