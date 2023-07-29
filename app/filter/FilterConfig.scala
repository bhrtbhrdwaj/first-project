package filter

import com.google.inject.Inject
import play.api.http.{DefaultHttpFilters, EnabledFilters}
import play.filters.gzip.GzipFilter

class FilterConfig @Inject()(defaultFilters: EnabledFilters,
                             gzip: GzipFilter,
                             log: ApplicationFilter
                        ) extends DefaultHttpFilters(defaultFilters.filters :+ gzip :+ log: _*)