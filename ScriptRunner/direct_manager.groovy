import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.bc.user.UserPropertyService;
import com.atlassian.jira.entity.property.EntityPropertyService.PropertyResult;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserManager;

ApplicationUser assignee = issue.getAssignee();
UserPropertyService propertyService = ComponentManager.getComponentInstanceOfType(UserPropertyService);
UserManager userManager = ComponentManager.getComponentInstanceOfType(UserManager);

PropertyResult propertyResult = propertyService.getProperty(assignee, assignee.getKey(), "managers");
def jsonValue = propertyResult.getEntityProperty().iterator().next().getValue();
def jsonSlurper = new groovy.json.JsonSlurper();
def managersMap = (Map<String, Object>) jsonSlurper.parseText(jsonValue);
def directManagerName = (String) managersMap.get("direct");
return userManager.getUserByName(directManagerName);
