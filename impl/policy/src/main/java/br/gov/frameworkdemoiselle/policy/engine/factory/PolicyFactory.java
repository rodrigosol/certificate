/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.policy.engine.factory;

import br.gov.frameworkdemoiselle.policy.engine.asn1.etsi.SignaturePolicy;
import br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.LPA;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;

public class PolicyFactory {

    private static final Logger logger = Logger.getLogger(PolicyFactory.class.getName());

    public static final PolicyFactory instance = new PolicyFactory();

    public static PolicyFactory getInstance() {
        return PolicyFactory.instance;
    }

    public SignaturePolicy loadPolicy(Policies policy) {
        SignaturePolicy signaturePolicy = new SignaturePolicy();
        InputStream is = this.getClass().getResourceAsStream(policy.getUrl());
        BufferedInputStream bis = new BufferedInputStream(is);
        ASN1Primitive primitive = this.readANS1FromStream(is);
        signaturePolicy.parse(primitive);
        return signaturePolicy;
    }

    public LPA loadLPA() {
        br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.LPA listaPoliticaAssinatura = new br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.LPA();
        InputStream is = this.getClass().getResourceAsStream(ListOfSubscriptionPolicies.version1.getUrl());
        ASN1Primitive primitive = this.readANS1FromStream(is);
        listaPoliticaAssinatura.parse(primitive);
        return listaPoliticaAssinatura;
    }

    public br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2.LPA loadLPAv2() {
        br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2.LPA listaPoliticaAssinatura = new br.gov.frameworkdemoiselle.policy.engine.asn1.icpb.v2.LPA();
        InputStream is = this.getClass().getResourceAsStream(ListOfSubscriptionPolicies.version2.getUrl());
        ASN1Primitive primitive = this.readANS1FromStream(is);
        listaPoliticaAssinatura.parse(primitive);
        return listaPoliticaAssinatura;
    }

    private ASN1Primitive readANS1FromStream(InputStream is) {
        ASN1InputStream asn1is = new ASN1InputStream(is);
        ASN1Primitive primitive = null;
        try {
            primitive = asn1is.readObject();
        } catch (IOException error) {
            throw new RuntimeException(error);
        } finally {
            try {
                asn1is.close();
            } catch (IOException error) {
                throw new RuntimeException(error);
            }
        }
        return primitive;
    }

    public enum Policies {

        AD_RB_CADES_1_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RB.der"),
        AD_RB_CADES_1_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RB_v1_1.der"),
        AD_RB_CADES_2_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RB_v2_0.der"),
        AD_RB_CADES_2_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RB_v2_1.der"),
        AD_RT_CADES_1_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RT.der"),
        AD_RT_CADES_1_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RT_v1_1.der"),
        AD_RT_CADES_2_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RT_v2_0.der"),
        AD_RT_CADES_2_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RT_v2_1.der"),
        AD_RV_CADES_1_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RV.der"),
        AD_RV_CADES_1_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RV_v1_1.der"),
        AD_RV_CADES_2_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RV_v2_0.der"),
        AD_RV_CADES_2_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RV_v2_1.der"),
        AD_RC_CADES_1_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RC.der"),
        AD_RC_CADES_1_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RC_v1_1.der"),
        AD_RC_CADES_2_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RC_v2_0.der"),
        AD_RC_CADES_2_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RC_v2_1.der"),
        AD_RA_CADES_1_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RA.der"),
        AD_RA_CADES_1_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RA_v1_1.der"),
        AD_RA_CADES_1_2("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RA_v1_2.der"),
        AD_RA_CADES_2_0("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RA_v2_0.der"),
        AD_RA_CADES_2_1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RA_v2_1.der"),
        AD_RA_CADES_2_2("/br/gov/frameworkdemoiselle/policy/engine/artifacts/PA_AD_RA_v2_2.der");

        private String url;

        private Policies(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public enum ListOfSubscriptionPolicies {

        version1("/br/gov/frameworkdemoiselle/policy/engine/artifacts/LPA.der"),
        version2("/br/gov/frameworkdemoiselle/policy/engine/artifacts/LPAv2.der");

        private String url;

        private ListOfSubscriptionPolicies(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
