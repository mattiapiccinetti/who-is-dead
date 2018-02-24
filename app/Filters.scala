import javax.inject.Inject

import play.api.http.DefaultHttpFilters
import play.filters.hosts.AllowedHostsFilter

class Filters @Inject() (allowedHostsFilter: AllowedHostsFilter)
  extends DefaultHttpFilters(allowedHostsFilter)