Then /^I swipe the view with id "([^\"]*)" to the (right|left)$/ do |id, direction|
  view = query("* id:'#{id}'").first
  raise "Haven't found view with id #{id}" if view.nil?
  x = view["rect"]["center_x"]
  y = view["rect"]["center_y"]
  fromX = x
  toX = direction == "right" ? 0 : 2 * x
  perform_action('drag_coordinates', fromX, y, toX, y)
end