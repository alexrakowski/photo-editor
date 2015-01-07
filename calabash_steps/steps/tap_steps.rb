Then /^I tap the text "([^\"]*)"$/ do |text|
  tap_mark text
end

Then /^I tap the text "([^\"]*)" and I should see text containing "([^\"]*)"$/ do |text_to_tap, text_to_see|
  tap_mark text_to_tap
  #wait_for_text(text_to_see, timeout: 10)
end

Then /^I tap the (\d+) view in layout with id "([^\"]*)"$/ do |index, id|
  index = index.to_i - 1
  tap_when_element_exists("* id:'#{id}' child * index:#{index}")
end


