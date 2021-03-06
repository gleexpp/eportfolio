/* $Name:  $ */
/* $Id: LearningDiff.java,v 1.13 2010/10/27 19:24:57 ajokela Exp $ */
/*
 * $Header: /opt/UMN-src/portfolio/src/org/portfolio/model/LearningDiff.java,v 1.13 2010/10/27 19:24:57 ajokela Exp $
 * $Revision: 1.13 $
 * $Date: 2010/10/27 19:24:57 $
 *
 * ============================================================================
 *
 * The contents of this file are subject to the OSPI License Version 1.0 (the
 * License).  You may not copy or use this file, in either source code or
 * executable form, except in compliance with the License.  You may obtain a
 * copy of the License at http://www.theospi.org/.
 * 
 * Software distributed under the License is distributed on an AS IS basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied.  See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * Copyrights:
 * 
 * Portions created by or assigned to The University of Minnesota are Copyright
 * (c) 2003 The University of Minnesota.  All Rights Reserved.  Contact
 * information for OSPI is available at http://www.theospi.org/.
 * 
 * Portions Copyright (c) 2003 the r-smart group, inc.
 * 
 * Portions Copyright (c) 2003 The University of Delaware.
 * 
 * Acknowledgements
 * 
 * Special thanks to the OSPI Users and Contributors for their suggestions and
 * support.
 */

package org.portfolio.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.portfolio.util.PortfolioConstants;

/**
 * This class encapsulates LearningDiff element object.
 *
 * @author	John Bush
 * @author      Jack Brown, University of Minnesota
 * @since	0.0
 * @version	$Revision: 1.13 $
 */
public class LearningDiff extends ElementBase {

    private static final long serialVersionUID = 8493257548363728721L;
	
    public java.lang.String getEntryName() {
        return (entryName != null ? entryName : EMPTY_STRING);
    }

    public void setEntryName(java.lang.String entryName) {
        this.entryName = entryName;
    }

    public java.lang.String getCondition() {
        return (condition != null ? condition : EMPTY_STRING);
    }

    public void setCondition(java.lang.String condition) {
        this.condition = condition;
    }

    public java.lang.String getMethod() {
        return (method != null ? method : EMPTY_STRING);
    }

    public void setMethod(java.lang.String method) {
        this.method = method;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (request.getParameter("viewfile.x") != null) return errors;

        if ((entryName == null) || (entryName.trim().length() == 0)) {
            errors.add("entryName", new ActionMessage("error.required.missing", "Name of condition"));
        } else if(entryName.trim().length() > PortfolioConstants.FIFTY_CHARS) {
            entryName = entryName.trim().substring(0, PortfolioConstants.FIFTY_CHARS);
            errors.add("entryName", new ActionMessage("error.lengthTooLong", "Name of condition", PortfolioConstants.FIFTY_CHARS_DESC));
        }
        
        if ((condition != null) && (condition.length() > PortfolioConstants.EIGHT_HUNDRED_WORDS)) {
            condition = condition.substring(0, PortfolioConstants.EIGHT_HUNDRED_WORDS);
            errors.add("condition", new ActionMessage("error.lengthTooLong", "Description of learning condition", PortfolioConstants.EIGHT_HUNDRED_WORDS_DESC));
        }
        if ((method != null) && (method.length() > PortfolioConstants.EIGHT_HUNDRED_WORDS)) {
            method = method.substring(0, PortfolioConstants.EIGHT_HUNDRED_WORDS);
            errors.add("method", new ActionMessage("error.lengthTooLong", "Alternative learning methods", PortfolioConstants.EIGHT_HUNDRED_WORDS_DESC ));
        }

        return errors;
    }


    public void setIsRemote(boolean isRemote) {
        this.isRemote = isRemote;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append(super.toString()).append("\n");

        // for this instance.
        buff.append("personId=").append(getPersonId()).append(",");
        buff.append("entryId=").append(getEntryId()).append(",");
        buff.append("dateCreated=").append(getDateCreated()).append(",");
        buff.append("modifiedDate=").append(getModifiedDate()).append(",");
        buff.append("entryName=").append(getEntryName()).append(",");
        buff.append("condition=").append(getCondition()).append(",");
        buff.append("method=").append(getMethod()).append(",");
        buff.append("is new? =>" + isNew());
        return buff.toString();
    }

    protected java.lang.String condition = null;
    protected java.lang.String method = null;
    protected boolean isRemote = false;
}
