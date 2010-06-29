/* Copyright 2008 Alin Dreghiciu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bodl.sandbox.paxwhite.internal;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Hello World Filter. Sets content type, page title and html tag.
 *
 * @author Alin Dreghiciu
 * @since 0.3.0, January 02, 2008
 */
class HelloWorldFilter
    implements Filter
{

    /**
     * Filter config.
     */
    private FilterConfig m_filterConfig;

    public void init( final FilterConfig filterConfig )
        throws ServletException
    {
        m_filterConfig = filterConfig;
    }

    public void doFilter( final ServletRequest request,
                          final ServletResponse response,
                          final FilterChain chain )
        throws IOException, ServletException
    {
        response.setContentType( "text/html" );

        final PrintWriter writer = response.getWriter();
        writer.println( "<html>" );
        writer.println( "<head><title>" + m_filterConfig.getInitParameter( "title" ) + "</title></head>" );

        chain.doFilter( request, response );

        writer.println( "</html>" );
    }

    public void destroy()
    {
        // do nothing
    }
}
