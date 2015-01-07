Then /^take picture$/ do
  screenshot_embed
end

Then /^I take a picture$/ do
  screenshot_embed
end

Then /^I take a picture named "([^\"]*)"$/ do |img_name|
  screenshot_embed(:name => img_name)
end

Then /^I take a screenshot$/ do
  screenshot_embed
end
